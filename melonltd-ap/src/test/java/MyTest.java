
import org.junit.Test;
import org.springframework.context.annotation.PropertySource;

import com.melonltd.naber.endpoint.util.Base64Service;

@PropertySource("classpath:/config.properties")
public class MyTest {
	
	@Test
	public void test() {
//		System.out.println(Base64Service.testDecode("JTJGbG9naW4="));
//		System.out.println(Base64Service.testDecode("JTJGYXBwJTJGYWRtaW4lMkZyZXN0YXVyYW50JTJGbGlzdA=="));
//		System.out.println(Base64Service.testDecode("JTJGYXBwJTJGYWRtaW4lMkZyZXN0YXVyYW50JTJGaGlkZGVuJTJGc3dpdGNo"));
//		System.out.println(Base64Service.testDecode("JTJGYXBwJTJGYWRtaW4lMkZyZXN0YXVyYW50JTJGY2FuJTJGZGlzY291bnQlMkZzd2l0Y2g="));
//		System.out.println(Base64Service.testDecode("JTJGYXBwJTJGYWRtaW4lMkZyZXN0YXVyYW50JTJGYWRk"));
//		System.out.println(Base64Service.testDecode("JTJGYXBwJTJGYWRtaW4lMkZyZXN0YXVyYW50JTJGdXBkYXRl"));
//		System.out.println(Base64Service.testDecode("JTJGYXBwJTJGYWRtaW4lMkZhZCUyRmxpc3Q="));
//		System.out.println(Base64Service.testDecode("JTJGYXBwJTJGYWRtaW4lMkZhZCUyRmFkZA=="));
//		System.out.println(Base64Service.testDecode("JTJGYXBwJTJGYWRtaW4lMkZhZCUyRnVwZGF0ZQ=="));
//		System.out.println(Base64Service.testDecode("JTJGYXBwJTJGYWRtaW4lMkZzdG9yZSUyRmNhdGVnb3J5JTJGbGlzdA=="));
//		System.out.println(Base64Service.testDecode("JTJGYXBwJTJGYWRtaW4lMkZzdG9yZSUyRmFyZWElMkZsaXN0"));
//		System.out.println(Base64Service.testDecode("JTJGYXBwJTJGYWRtaW4lMkZzdG9yZSUyRmNhdGVnb3J5cyUyRnVwZGF0ZQ=="));
//		System.out.println(Base64Service.testDecode("JTJGYXBwJTJGYWRtaW4lMkZzdG9yZSUyRmFyZWFzJTJGdXBkYXRl"));
//		System.out.println(Base64Service.testDecode("JTJGYXBwJTJGYWRtaW4lMkZvcmRlciUyRmxpc3Q="));
//		System.out.println(Base64Service.testDecode("JTJGYXBwJTJGYWRtaW4lMkZzdGF0aXN0aWNzJTJGYnklMkZtb250aA=="));
		System.out.println(Base64Service.testEncode("/common/school/divided/list"));
		
	}
}
