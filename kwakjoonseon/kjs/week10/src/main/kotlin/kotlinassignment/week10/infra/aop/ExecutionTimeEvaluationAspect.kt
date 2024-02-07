package kotlinassignment.week10.infra.aop

import io.github.oshai.kotlinlogging.KotlinLogging
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component

@Component
@Aspect
class ExecutionTimeEvaluationAspect {

    private val logger = KotlinLogging.logger {  }

    @Around("@annotation(kotlinassignment.week10.infra.aop.EvaluateExecutionTime)")
    fun run(joinPoint: ProceedingJoinPoint): Any {

        val startTime = System.currentTimeMillis()

        val result = joinPoint.proceed()

        val endTime = System.currentTimeMillis()

        val methodName = joinPoint.signature.name
        val methodArguments = joinPoint.args

        val elapsedTimeMillis = endTime - startTime

        logger.info { "Method Name: $methodName | Arguments: ${methodArguments.joinToString(", ")} | Execution Time: ${elapsedTimeMillis}ms" }

        return result
    }
}
