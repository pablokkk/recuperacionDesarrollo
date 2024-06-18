package com.example.notas;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.List;

public class MainController {

    @FXML
    private TableView<Alumno> tableView;

    @FXML
    private TableColumn<Alumno, String> nombreColumn;

    @FXML
    private TableColumn<Alumno, String> apellidosColumn;

    @FXML
    private TableColumn<Alumno, Double> adColumn;

    @FXML
    private TableColumn<Alumno, Double> sgeColumn;

    @FXML
    private TableColumn<Alumno, Double> diColumn;

    @FXML
    private TextField nombreField;

    @FXML
    private TextField apellidosField;

    @FXML
    private Spinner<Double> adSpinner;

    @FXML
    private Spinner<Double> sgeSpinner;

    @FXML
    private Spinner<Double> diSpinner;

    private List<Alumno> listaAlumnos;

    public void initialize() {
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        apellidosColumn.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        adColumn.setCellValueFactory(new PropertyValueFactory<>("ad"));
        sgeColumn.setCellValueFactory(new PropertyValueFactory<>("sge"));
        diColumn.setCellValueFactory(new PropertyValueFactory<>("di"));

        listaAlumnos = new ArrayList<>();

        listaAlumnos.add(new Alumno("Juan", "García", 7.5, 6.8, 8.0));
        listaAlumnos.add(new Alumno("María", "López", 6.0, 6.5, 5.5));
        listaAlumnos.add(new Alumno("Pedro", "Martínez", 8.0, 7.0, 7.5));

        tableView.getItems().addAll(listaAlumnos);
    }

    @FXML
    private void handleAgregarAlumno(ActionEvent event) {
        String nombre = nombreField.getText().trim();
        String apellidos = apellidosField.getText().trim();
        double ad = adSpinner.getValue();
        double sge = sgeSpinner.getValue();
        double di = diSpinner.getValue();


        if (nombre.isEmpty() || apellidos.isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Debe ingresar el nombre y apellidos del alumno.");
            alert.showAndWait();
            return;
        }

        if (ad < 0 || ad > 10 || sge < 0 || sge > 10 || di < 0 || di > 10) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Las notas deben estar en el rango de 0 a 10.");
            alert.showAndWait();
            return;
        }


        Alumno nuevoAlumno = new Alumno(nombre, apellidos, ad, sge, di);
        listaAlumnos.add(nuevoAlumno);
        tableView.getItems().add(nuevoAlumno);


        nombreField.clear();
        apellidosField.clear();
        adSpinner.getValueFactory().setValue(0.0);
        sgeSpinner.getValueFactory().setValue(0.0);
        diSpinner.getValueFactory().setValue(0.0);
    }

    @FXML
    private void handleMostrarInfoAlumno() {
        Alumno alumnoSeleccionado = tableView.getSelectionModel().getSelectedItem();
        if (alumnoSeleccionado != null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Información del Alumno");
            alert.setHeaderText(null);

            StringBuilder mensaje = new StringBuilder();
            mensaje.append("Nombre: ").append(alumnoSeleccionado.getNombre()).append(" ").append(alumnoSeleccionado.getApellidos()).append("\n");

            if (alumnoSeleccionado.tieneAprobadosTodos()) {
                mensaje.append("Nota media: ").append(alumnoSeleccionado.calcularMedia()).append("\n");
            } else {
                int modulosSuspensos = 3 - (alumnoSeleccionado.getAd() >= 5 ? 0 : 1) +
                        (alumnoSeleccionado.getSge() >= 5 ? 0 : 1) +
                        (alumnoSeleccionado.getDi() >= 5 ? 0 : 1);
                mensaje.append("Número de módulos suspensos: ").append(modulosSuspensos).append("\n");
            }

            alert.setContentText(mensaje.toString());
            alert.showAndWait();
        }
    }
}
