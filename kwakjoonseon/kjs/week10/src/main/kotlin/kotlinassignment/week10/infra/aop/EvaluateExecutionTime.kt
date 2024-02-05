package kotlinassignment.week10.infra.aop

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class EvaluateExecutionTime
