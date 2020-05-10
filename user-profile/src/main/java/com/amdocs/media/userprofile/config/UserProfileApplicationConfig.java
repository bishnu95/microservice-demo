package com.amdocs.media.userprofile.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.amdocs.media.model.UserProfileInfo;

import brave.sampler.Sampler;

@Configuration
@PropertySource("classpath:log.message")
@EnableKafka
public class UserProfileApplicationConfig {

	@Value("${bootstrap.port}")
	private String bootstrapPort;

	@Value("${group.id")
	private String groupId;

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public Sampler sampler() {
		return Sampler.ALWAYS_SAMPLE;
	}

	@Bean
	public ConsumerFactory<String, UserProfileInfo> consumerFactory() {
		Map<String, Object> configs = new HashMap<>();
		configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapPort);
		configs.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
		configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		return new DefaultKafkaConsumerFactory<>(configs, new StringDeserializer(),
				new JsonDeserializer<>(UserProfileInfo.class));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, UserProfileInfo> kafkaListnerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, UserProfileInfo> listnerFactory = new ConcurrentKafkaListenerContainerFactory<>();
		listnerFactory.setConsumerFactory(consumerFactory());
		return listnerFactory;
	}

}
