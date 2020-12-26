
package com.xantrix.webapp.UnitTest.RepositoryTest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.xantrix.webapp.Application;
import com.xantrix.webapp.entities.DettListini;
import com.xantrix.webapp.repository.PrezziRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;


@TestPropertySource(locations="classpath:application-list1.properties")
@ContextConfiguration(classes = Application.class)
@SpringBootTest
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PrezziRepositoryTest
{
    @Autowired
    private PrezziRepository prezziRepository;

    @Test
	public void TestfindByCodArtAndIdList1()
	{
        String CodArt = "002000301";
        String IdList = "1";

        assertThat(prezziRepository.findByCodArtAndIdList(CodArt, IdList)).extracting(DettListini::getPrezzo).isEqualTo(1.07);
				//.containsOnly(1.07,1.07);
    }

    @Test
	public void TestfindByCodArtAndIdList2()
	{
        String CodArt = "002000301";
        String IdList = "2";

        assertThat(prezziRepository.findByCodArtAndIdList(CodArt, IdList)).extracting(DettListini::getPrezzo).isEqualTo(0.87);
			//	.containsOnly(0.87,0.87);
    }

    @Test
    public void TestErrfindByCodArtAndIdList()
	{
        String CodArt = "002000301";
        String IdList = "3";

        assertThat(prezziRepository.findByCodArtAndIdList(CodArt, IdList)).isNull();
    }
    
    
}