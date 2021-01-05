package com.example.demo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.Datamapper;

@Service
public class ListService {
	@Autowired
	Datamapper datamapper;
	
	public List<Map<String, Object>> list(){
		
		return datamapper.list();
	}
}
