package bp.app.model

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import tornadofx.*
import java.time.LocalDateTime

class Measurement : JsonModel {
    val sysProperty = SimpleIntegerProperty()
    var sys by sysProperty
    val diaProperty = SimpleIntegerProperty()
    var dia by diaProperty
    val pulseProperty = SimpleIntegerProperty()
    val pulse by pulseProperty
    val timeProperty = SimpleObjectProperty<LocalDateTime>(LocalDateTime.now())
    var time by timeProperty

    override fun toString(): String {
        return "$time - $sys - $dia - $pulse"
    }
}

class MeasurementModel : ItemViewModel<Measurement>() {
    val sys = bind(Measurement::sysProperty)
    val dia = bind(Measurement::diaProperty)
    val pulse = bind(Measurement::pulseProperty)
    val time = bind(Measurement::timeProperty, defaultValue = LocalDateTime.now())

    override fun onCommit() {
        println("committing")
    }
}
