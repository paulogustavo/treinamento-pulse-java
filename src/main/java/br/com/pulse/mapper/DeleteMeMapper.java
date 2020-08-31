package br.com.pulse.mapper;

import br.com.pulse.model.DeleteMe;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface DeleteMeMapper {

  DeleteMe recuperar();

}