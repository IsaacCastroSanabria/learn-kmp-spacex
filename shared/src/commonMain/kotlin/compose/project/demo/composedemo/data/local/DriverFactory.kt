package compose.project.demo.composedemo.data.local

expect class DriverFactory {
    fun createDriver(): SqlDriver
}