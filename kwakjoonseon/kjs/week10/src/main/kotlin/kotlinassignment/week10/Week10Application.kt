package kotlinassignment.week10

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class Week10Application

fun main(args: Array<String>) {
    runApplication<Week10Application>(*args)
}
