package id.ac.ubaya.protectcare71

data class CheckIn(val id: String, val nama: String)
{
    override fun toString(): String {
        return nama
    }
}
