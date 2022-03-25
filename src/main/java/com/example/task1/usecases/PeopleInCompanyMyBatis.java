package com.example.task1.usecases;

import com.example.task1.mybatis.dao.PersonMapper;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

@Model
public class PeopleInCompanyMyBatis {

    @Inject
    private PersonMapper personMapper;

    @Transactional
    public void deletePerson(Integer id) {
        personMapper.deleteByPrimaryKey(id);
    }
}
