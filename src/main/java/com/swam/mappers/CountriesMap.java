package com.swam.mappers;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * Created by gkou on 2016/08/25.
 */
@Mapper
public interface CountriesMap {
   Map select(String id);
}
