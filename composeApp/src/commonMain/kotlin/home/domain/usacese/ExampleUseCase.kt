package home.domain.usacese

fun interface ExampleUseCase {
    suspend operator fun invoke(): String
}