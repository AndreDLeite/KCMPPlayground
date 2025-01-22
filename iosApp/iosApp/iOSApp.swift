import SwiftUI
import GoogleMaps

@main
struct iOSApp: App {
    init() {
        GMSServices.provideAPIKey("")
    }
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
