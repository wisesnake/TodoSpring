package org.zerock.springex.sample;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.sql.Connection;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")


//이 경우, @ExtendWith(SpringExtension.class) 어노테이션은 JUnit 5에서 제공하는 Spring 확장을 사용하여 테스트 클래스에 스프링 기능을 적용합니다.
// 또한, @ContextConfiguration 어노테이션을 사용하여 해당 테스트 클래스에 대한 컨텍스트 설정 파일의 위치를 지정합니다.
// 이로써 스프링 컨텍스트가 테스트에 적용되고, 스프링이 관리하는 빈들을 주입받을 수 있습니다.
//이러한 설정을 추가함으로써 테스트 클래스는 스프링의 특정 기능 및 빈 주입을 활용할 수 있게 됩니다.

public class SampleTests {


    @Autowired
    private SampleService sampleService;

    @Autowired
    private DataSource dataSource;


    @Test
    void testService() {
        Assertions.assertNotNull(sampleService);
    }

    @Test
    void testConnection() throws Exception {
        Connection connection = dataSource.getConnection();
        log.info("커넥션은 " + connection);
        Assertions.assertNotNull(connection);
        connection.close();


    }
}
