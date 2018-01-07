package bp.view

import bp.app.TableView
import javafx.beans.property.Property
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.value.ObservableValue
import javafx.event.EventTarget
import tornadofx.*
import tornadofx.control.DateTimePicker
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainView : View() {

    override val root = hbox {
        add<TableView>()
    }
}

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

fun DateTimePicker.bind(property: ObservableValue<LocalDateTime>, readonly: Boolean = false) {
    ViewModel.register(valueProperty(), property)
    if (readonly || (property !is Property<*>)) dateTimeValueProperty().bind(property) else dateTimeValueProperty().bindBidirectional(property as Property<LocalDateTime>)
}

fun EventTarget.datetimepicker(op: DateTimePicker.() -> Unit = {}) = opcr(this, DateTimePicker(), op)
fun EventTarget.datetimepicker(property: Property<LocalDateTime>, op: DateTimePicker.() -> Unit = {}) = datetimepicker().apply {
    bind(property)
    op(this)
}
