import { Component } from '@angular/core';
import { AdministradorComponent } from '../usuarios/administrador/administrador.component';
import { OperadorComponent } from '../usuarios/operador/operador.component';
import { RecepcionistaComponent } from '../usuarios/recepcionista/recepcionista.component';
import { Empleado } from '../../model/empleado';
import { FormControl, FormGroup, FormsModule, Validators } from '@angular/forms';
import { EmpleadoService } from '../../services/empleado.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-inicio-sesion',
  standalone: true,
  imports: [AdministradorComponent, OperadorComponent, RecepcionistaComponent, FormsModule],
  templateUrl: './inicio-sesion.component.html',
  styleUrl: './inicio-sesion.component.css'
})
export class InicioSesionComponent {

  empleado !: Empleado;
  empleadoLogeado!: Empleado;

  isLoggedIn: boolean = false;
  rol = 7;
  nombre = '';
  formData = {
    username: '',
    password: ''
  };

  loginForm = new FormGroup({
    username: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required)
  });

  get usernameControl(): FormControl {
    return this.loginForm.get('username') as FormControl;
  }

  get passwordControl(): FormControl {
    return this.loginForm.get('password') as FormControl;
  }

  constructor(private empleadoService: EmpleadoService, private router: Router) {
    this.empleado = new Empleado();
  }

  onSubmit() {
    console.log("datos del form: ", this.formData);
  }

  iniciarSesion() {
    console.log("entrando a sigIn")
    this.empleado.username = this.formData.username;
    this.empleado.password = this.formData.password;

    console.log("username = " + this.empleado.username + ", password = " + this.empleado.password);

    this.empleadoService.iniciarSesion(this.empleado).subscribe(loggedEmpleado => {
        if (loggedEmpleado) {
          this.empleadoLogeado = loggedEmpleado;
          this.isLoggedIn = true;
          this.rol = loggedEmpleado.rol;
          this.nombre = loggedEmpleado.nombre;
          console.log('credenciales correctas');
          this.formData.username = '';
          this.formData.password = '';
        } else {
          console.log('empleado no encontrado');
        }
      }, error => {
        console.log('error al buscar');
      }

      );
  }

  cambiarEstado(nuevoEstado: boolean) {
    this.isLoggedIn = nuevoEstado;
  }

}
