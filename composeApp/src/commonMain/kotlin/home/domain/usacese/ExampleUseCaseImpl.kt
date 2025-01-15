package home.domain.usacese

class ExampleUseCaseImpl: ExampleUseCase {
    override suspend fun invoke(): String {
        val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
        return (1..10)
            .map { allowedChars.random() }
            .joinToString("")
    }
}