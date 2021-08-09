package in.anand.rest;

import java.time.Duration;
import java.util.Date;
import java.util.stream.Stream;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import in.anand.binding.CustomerEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@RestController
public class CustomerEventRestController {
	
	@GetMapping(value="/event",produces= {"application/json"})
	public ResponseEntity<Mono<CustomerEvent>> invokeEvent()
	{
		CustomerEvent event = new CustomerEvent("kiran",new Date());
			Mono<CustomerEvent> mono = Mono.just(event);
			ResponseEntity<Mono<CustomerEvent>> responseEntity = new ResponseEntity<Mono<CustomerEvent>>(mono,HttpStatus.OK);
			System.out.println(responseEntity);
			return responseEntity;
		
		
	}
	
	
	@GetMapping(value="/events",produces=MediaType.TEXT_EVENT_STREAM_VALUE)
	public ResponseEntity<Flux<CustomerEvent>> invokeEvents()
	{
		CustomerEvent event = new CustomerEvent("kiran",new Date());
			 Stream<CustomerEvent> stream = Stream.generate(()->event);
			 Flux<CustomerEvent> flux = Flux.fromStream(stream);
			 Flux<Long> interval = Flux.interval(Duration.ofSeconds(3));
			 Flux<Tuple2<Long, CustomerEvent>> zip = Flux.zip(interval,flux);
			 Flux<CustomerEvent> map = zip.map(Tuple2::getT2);
			 ResponseEntity responseEntity = new ResponseEntity<Flux<CustomerEvent>>(map,HttpStatus.OK);
			 System.out.println(responseEntity);
			 return responseEntity;
			 
			 
		
		
	}


}
