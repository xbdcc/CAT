package com.carlos.cat

import javafx.application.Application
import javafx.beans.property.SimpleStringProperty
import javafx.beans.property.StringProperty
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.fxml.Initializable
import javafx.scene.Scene
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import javafx.scene.control.TextField
import javafx.scene.control.cell.TextFieldTableCell
import javafx.scene.input.MouseButton
import javafx.stage.Stage
import org.junit.Test
import java.net.URL
import java.util.*

/**
 * Created by Carlos on 2018/7/12.
 */
class TableViewTest : Application(), Initializable {

    @FXML
    lateinit var key: TextField
    @FXML
    lateinit var value: TextField
    @FXML
    lateinit var tableView: TableView<TableVO>
    @FXML
    lateinit var firstColumn: TableColumn<TableVO, String>
    @FXML
    lateinit var secondColumn: TableColumn<TableVO, String>
    lateinit var tableVOs: ObservableList<TableVO>


    override fun initialize(location: URL?, resources: ResourceBundle?) {
        tableVOs = FXCollections.observableArrayList()
        tableView.items = tableVOs

        firstColumn.setCellValueFactory { cellData ->
            cellData.value.key
        }

        secondColumn.setCellValueFactory { e -> e.value.value
        }

        secondColumn.setCellFactory(TextFieldTableCell.forTableColumn())

        tableView.setOnMouseReleased {event ->
            val button = event.button
            when(button) {
                MouseButton.PRIMARY -> println("left"+tableView.selectionModel.selectedCells)
                MouseButton.SECONDARY -> println("right"+tableView.selectionModel.selectedIndex)
            }
        }

        val a =  {
            var b = ""
            var c ="fdf"
        }

    }


    @FXML
    fun add() {
        addData()
    }

    fun addData() {
        tableVOs.add(TableVO(key.text, value.text))
    }

    class TableVO {
        var key: StringProperty
        var value: StringProperty

        constructor(key: String, value: String) {
            this.key = SimpleStringProperty(key)
            this.value = SimpleStringProperty(value)
        }

        fun getKey(): String {
            return key.get()
        }

        fun getValue(): String {
            return value.get()
        }

    }

    @Throws(Exception::class)
    override fun start(primaryStage: Stage) {

        val fxmlLoader = FXMLLoader()
        fxmlLoader.location = MainApp::class.java.getResource("/tableview.fxml")
        val scene = Scene(fxmlLoader.load())
        primaryStage.scene = scene
        primaryStage.show()

    }

    @Test
    fun startApp() {
        Application.launch(TableViewTest::class.java)
    }


}