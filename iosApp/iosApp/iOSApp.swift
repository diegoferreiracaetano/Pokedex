import SwiftUI
import composeApp

@main
struct iOSApp: App {

    init() {
        KoinModuleKt.doInitKoin() // Liga o Koin
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}