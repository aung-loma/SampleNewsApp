package co.just.simple.myapplication.model

enum class ProfileTabItems(val title: String, val count: Int) {
    PUBLISHED(
      "Published" , 24
    ),
    PENDING("Pending",10),
    DECLINED("Declined",5),
    DRAFT("Draft",2)
}