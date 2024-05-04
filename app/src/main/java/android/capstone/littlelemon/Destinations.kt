package android.capstone.littlelemon

interface Destinations{
    val route : String
}

object onBoarding : Destinations{
    override val route: String = "onboarding"
}
object home : Destinations{
    override val route: String = "home"
}

object profile : Destinations{
    override val route: String = "profile"
}