package net.usikkert.kouchat.misc;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class My_Class_Testing_Topic {
	
	Topic instance;
	
	@Before
	public void setUp() {
		instance = new Topic("Topic", "nickname", 2);
	}

	@Test
	public void getNickShouldReturnNick() {
		assertEquals("nickname", instance.getNick());
	}
	
	@Test
	public void getTimeShouldReturnTime() {
		assertEquals(2, instance.getTime());
	}
	
	@Test
	public void getTopicShouldReturnTopic() {
		assertEquals("Topic", instance.getTopic());
	}
	
	@Test
	public void hasTopicShouldReturnTrue() {
		assertTrue(instance.hasTopic());
	}
	
	@Test
	public void toStringShouldReturnAStringOfTopicAndNickName() {
		assertEquals("Topic (nickname)", instance.toString());
	}
	
	@Test
	public void resetTopicShouldResetTopic() {
		instance.resetTopic();
		assertEquals(0, instance.getTime());
		assertEquals("", instance.getTopic());
		assertEquals("", instance.getNick());
	}
	
	@Test
	public void testChangeTopicByPassInTopic() {
		Topic temp = new Topic("newTopic", "newNick", 1);
		instance.changeTopic(temp);
		
		assertEquals(temp.getTime(), instance.getTime());
		assertEquals(temp.getTopic(), instance.getTopic());
		assertEquals(temp.getNick(), instance.getNick());
	}
	
	@Test
	public void testChangeTopicByParameter() {
		instance.changeTopic("newTopic", "newNick", 1);
	
		assertEquals(1, instance.getTime());
		assertEquals("newTopic", instance.getTopic());
		assertEquals("newNick", instance.getNick());
	}
	
	
	
	
	
	
}
