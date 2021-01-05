package kr.co.kyobo.common.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Data
@ConfigurationProperties(prefix = "sftp.elk")
public class SftpService {

	private String host;
	private int port;
	private String user;
	private String password;
	
	private Session session = null;
    private Channel channel = null;
    private ChannelSftp channelSftp = null;
    private ChannelExec channelExec = null;
	    
    /**
     * 서버와 연결에 필요한 값들을 가져와 초기화 시킴
     *
     * @param host
     *            서버 주소
     * @param userName
     *            접속에 사용될 아이디
     * @param password
     *            비밀번호
     * @param port
     *            포트번호
     */
    public int init() {
    	
    	JSch jsch = new JSch();
        try {
            session = jsch.getSession(user, host, port);
            session.setPassword(password);

            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect();

            channel = session.openChannel("sftp");
            channel.connect();
        } catch (JSchException e) {
            e.printStackTrace();
            return -1;
        }

        channelSftp = (ChannelSftp) channel;
        return 0;
    }

    /**
     * 하나의 파일을 업로드 한다.
     *
     * @param dir
     *            저장시킬 주소(서버)
     * @param file
     *            저장할 파일
     */
    public void upload(String dir, File file) {

        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            
            String[] fileNameArr = file.getName().split("\\.");
            String newFileName = fileNameArr[0] + "_" + Math.abs(new Random().nextInt()) + "." + fileNameArr[1];
            
            channelSftp.cd(dir);
            channelSftp.put(in, newFileName);
        } catch (SftpException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 하나의 파일을 다운로드 한다.
     *
     * @param dir
     *            저장할 경로(서버)
     * @param downloadFileName
     *            다운로드할 파일
     * @param path
     *            저장될 공간
     */
    public void download(String dir, String downloadFileName, String path) {
        InputStream in = null;
        FileOutputStream out = null;
        try {
            channelSftp.cd(dir);
            in = channelSftp.get(downloadFileName);
        } catch (SftpException e) {
            e.printStackTrace();
        }

        try {
            out = new FileOutputStream(new File(path));
            int i;
            
            if(in != null)
            {
	            while ((i = in.read()) != -1) {
	                out.write(i);
	            }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
            	out.close();
            	
            	if(in != null)
            		in.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    
    /**
     * 하나의 파일을 삭제 한다.
     *
     * @param dir
     *            파일 경로(서버)
     * @param downloadFileName
     *            삭제할 파일
     */
    public void rmFile(String dir, String rmFileName) {

        try {
            channelSftp.cd(dir);
            channelSftp.rm(rmFileName);
        } catch (SftpException e) {
            e.printStackTrace();
        }
    }

    /**
     * 서버와의 연결을 끊는다.
     */
    public void disconnection() {
        channelSftp.quit();
        channelSftp.disconnect();
        channel.disconnect();
        session.disconnect();
    }
    
    
    /**
     * 서버와 연결에 필요한 값들을 가져와 초기화 시킴
     *
     * @param host
     *            서버 주소
     * @param userName
     *            접속에 사용될 아이디
     * @param privateKey
     *            키( ex - aws key.pem )
     * @param port
     *            포트번호
     */
    public void initSSHKey(String privateKey) {
        JSch jsch = new JSch();
        try {
            jsch.addIdentity(privateKey);
            session = jsch.getSession(user, host, port);
            // session.setPassword(password);

            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect();

            channelExec = (ChannelExec) session.openChannel("exec");
            channelExec.setPty(true);
        } catch (JSchException e) {
            e.printStackTrace();
        }
        //channelExec = (ChannelExec) channel;
    }
    
    
    /**
     * 서버와 연결에 필요한 값들을 가져와 초기화 시킴
     *
     * @param host
     *            서버 주소
     * @param userName
     *            접속에 사용될 아이디
     * @param password
     *            비밀번호
     * @param port
     *            포트번호
     */
    public void initSSHPassword() {
        JSch jsch = new JSch();
        try {
            session = jsch.getSession(user, host, port);
            session.setPassword(password);

            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect();

            channelExec = (ChannelExec) session.openChannel("exec");
            channelExec.setPty(true);
        } catch (JSchException e) {
            e.printStackTrace();
        }
    }    
    
    
    /**
     * 명령어를 실행 시킨다.
    *
    * @param command
    *            실행시킬 명령어
    */
    public String exec(String command) {
    	String output = "";
    	
    	try {
    		//실행할 명령어를 설정한다.
    		channelExec.setCommand(command);

//    		OutputStream out = channelExec.getOutputStream();
    		InputStream in = channelExec.getInputStream();
//    		InputStream err = channelExec.getErrStream();

    		// 명령어를 실행한다.
    		channelExec.connect(15000);

    		
    		byte[] buf = new byte[1024];
    		int length;

    		while ((length = in.read(buf)) != -1){
    			output += new String(buf,0,length);
    		//	log.info("=== command result : " + new String(buf,0,length));
    		}

    		if(output.trim().matches(".*invalid.*")) //invalid 형태이면
    		{
    		//	log.info("=== invalid command.. : " + output.trim());
    		} else if(output.trim().matches(".*command not found.*")) //command not found 형태이면
    		{
    		//	log.info("=== invalid command.. : " + output.trim());
    		}
    		
    	} catch (JSchException e) {
    		e.printStackTrace();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	
    	return output;
    }
    
    /**
     * 서버와의 연결을 끊는다.
     */
    public void disconnectionSSH() {
        channelExec.disconnect();
        session.disconnect();
    }	

}