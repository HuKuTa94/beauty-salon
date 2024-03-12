rootProject.name = "beauty-salon"

include("common")
include("feature-sample")
include("feature-sample:domain")
include("feature-sample:application")
include("feature-sample:application:port:in")
include("feature-sample:application:port:out")
include("feature-sample:adapter")
include("feature-sample:adapter:in:web")
include("feature-sample:adapter:out:persistence-in-memory")
include("feature-sample:adapter:out:persistence-postgres")
