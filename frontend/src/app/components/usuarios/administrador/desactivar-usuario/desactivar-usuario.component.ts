import { Component } from '@angular/core';
import { FormGroup, FormsModule, Validators } from '@angular/forms';
import { EmpleadoService } from '../../../../services/empleado.service';
import { Empleado } from '../../../../model/empleado';

@Component({
  selector: 'app-desactivar-usuario',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './desactivar-usuario.component.html',
  styleUrl: './desactivar-usuario.component.css'
})
export class DesactivarUsuarioComponent {

  constructor(private empleadoService: EmpleadoService) {

    this.empleado = new Empleado();
  }

  empleado !: Empleado;

  FormData = {
    idEmpleado: ''
  }

  form = new FormGroup({
    idEmpleado: new FormGroup('', Validators.required)
  });

  onSubmit() {
    console.log('contenido: ' + this.FormData);
  }

  desactivarUsuario() {
    console.log('entrandoa a desactivarRuta()');

    this.empleado.id = parseInt(this.FormData.idEmpleado);

    console.log('empleado a desactivar:' + this.empleado.id);

    this.empleadoService.desactivarEmpleado(this.empleado.id).
      subscribe(
        (data) => {
          alert('empleado desactivado');
          this.FormData.idEmpleado = '';
        },
        (error) => {
          alert('error al desactivar empleado')
        }
      );
  }
}
