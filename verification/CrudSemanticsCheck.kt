data class TravelPlace(
    val id: Long,
    val name: String,
    val visited: Boolean = false,
)

class FakePersistentStore {
    private val rows = linkedMapOf<Long, TravelPlace>()
    private var nextId = 1L

    fun insert(name: String): Long {
        val id = nextId++
        rows[id] = TravelPlace(id, name)
        return id
    }
    fun get(id: Long) = rows[id]
    fun update(place: TravelPlace) { rows[place.id] = place }
    fun delete(place: TravelPlace) { rows.remove(place.id) }
    fun all() = rows.values.toList()
}

fun main() {
    val db = FakePersistentStore()
    val id = db.insert("Карпати")
    check(db.get(id)?.name == "Карпати")
    val updated = db.get(id)!!.copy(visited = true)
    db.update(updated)
    check(db.get(id)?.visited == true)
    check(db.all().size == 1)
    db.delete(updated)
    check(db.all().isEmpty())
    println("CRUD semantics OK: insert/read/update/delete")
}
