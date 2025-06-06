package uk.co.codeleap.careers.mappers;

import org.mapstruct.Mapper;
import uk.co.codeleap.careers.dtos.NetworkPostRecordDTO;
import uk.co.codeleap.careers.models.NetworkPost;

@Mapper(componentModel = "spring")
public interface NetworkPostMapper {

    NetworkPost toNetworkPost(NetworkPostRecordDTO postDTO);


}
