package com.carlos.cat.part

import com.carlos.cat.vo.StepVO
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import javafx.scene.control.cell.TextFieldTableCell
import javafx.scene.input.MouseButton
import javafx.scene.layout.Pane

/**
 * Created by Carlos on 2018/7/9.
 */
class DetailsPart(val pane: Pane) {

    private lateinit var tableView: TableView<StepVO>
    private lateinit var stepVOs: ObservableList<StepVO>
//    private lateinit var firstColumn: TableColumn<StepVO, String>

    fun init() {
        tableView = pane.lookup("#tableView") as TableView<StepVO>
        val b = pane.lookup("#tableView")
        println(b)
        val c =tableView.columns
        val firstColumn = tableView.columns[0] as TableColumn<StepVO, String>
//        firstColumn = pane.lookup("#firstColumn") as TableColumn<StepVO, String?>
        val secondColumn = tableView.columns[1] as TableColumn<StepVO, String>
        stepVOs = FXCollections.observableArrayList()
        tableView.items = stepVOs
//
        firstColumn.setCellValueFactory { cellData ->
            cellData.value.key
        }
//
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
    }

    fun addItems() {
        val stepVO = StepVO("testv", "v")
        addItem( stepVO)
    }

    fun addItem(stepVO: StepVO, changePosition: Boolean = true) {
        val index = tableView.selectionModel.selectedIndex
        if (stepVOs.size > 0) {
            stepVOs.add(index + 1, stepVO)
            tableView.selectionModel.select(tableView.selectionModel.selectedIndex + 1)
        } else {
            stepVOs.add(stepVO)
            tableView.selectionModel.select(0)
        }
        tableView.scrollTo(tableView.selectionModel.selectedIndex)
    }


}