package compose.project.demo.composedemo.di.modules

import org.koin.dsl.module

val sharedModule = module {
    // Aquí incluimos todos los módulos que creaste anteriormente
    // El platformModule() se llama como función porque es un expect/actual
    includes(dataModule, domainModule, presentationModule, networkModule, platformModule())
}