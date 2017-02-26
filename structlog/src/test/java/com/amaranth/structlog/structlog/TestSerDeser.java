package structlog;

import java.io.IOException;
import java.util.Date;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.junit.Assert;
import org.junit.Test;

import com.amaranth.structlog.struct.StructLog;
import com.amaranth.structlog.struct.StructLogFactory;
import com.amaranth.structlog.struct.util.SerDeserHelper;

public class TestSerDeser {


	@Test
	public void test1() throws JsonGenerationException, JsonMappingException, IOException
	{
		TestRequest tr = new TestRequest("test1", 1234, new Date());
		String trJson = SerDeserHelper.toJsonString(tr, TestRequest.class);
		System.out.println(trJson);
		try(StructLog sl = StructLogFactory.getRootStructLog("c1",null))
		{
			sl.setInputAsObject(tr);
			String input = sl.getInput();
			System.out.println(input);
			TestRequest tr1 = SerDeserHelper.getObjectFromJsonString(input, TestRequest.class);
			String tr1Json = SerDeserHelper.toJsonString(tr1, TestRequest.class);
			Assert.assertTrue(tr1Json.equals(trJson));
		}
	}
}
