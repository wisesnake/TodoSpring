package org.zerock.springex.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration//클래스가 하나 이상의 @Bean 메소드를 선언하고 런타임 시 해당 Bean 에 대한 Bean 정의 및
                //서비스 요청을 생성하기 위해 Spring 컨테이너에서 처리될 수 있음을 나타냅니다.
public class ModelMapperConfig {

    @Bean // 메서드가  Spring 컨테이너에서 관리할 Bean을 생성함을 나타냅니당
    public ModelMapper getMapper(){
        ModelMapper modelMapper = new ModelMapper();
        //private으로 선언된 필드도 접근 가능하도록 설정함.
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.STRICT);

        return modelMapper;

    }
}
