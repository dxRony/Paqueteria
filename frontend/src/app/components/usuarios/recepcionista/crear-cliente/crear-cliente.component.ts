import { Component } from '@angular/core';
import { FormGroup, FormsModule, Validators } from '@angular/forms';
import { ClienteService } from '../../../../services/cliente.service';
import { Cliente } from '../../../../model/cliente';

@Component({
  selector: 'app-crear-cliente',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './crear-cliente.component.html',
  styleUrl: './crear-cliente.component.css'
})
export class CrearClienteComponent {

  constructor(private clienteService: ClienteService) {
    this.cliente = new Cliente();

  }
  cliente!: Cliente;
  clienteCreado!: Cliente;

  formData = {
    nitCliente: '',
    nombre: ''
  }

  form = new FormGroup({
    nitCliente: new FormGroup('', Validators.required),
    nombre: new FormGroup('', Validators.required)
  });

  onSubmit() {
    console.log('contenido' + this.formData);
  }

  crearCliente() {
    this.cliente.nit = parseInt(this.formData.nitCliente);
    this.cliente.nombre = this.formData.nombre;

    this.clienteService.crearCliente(this.cliente).subscribe
      (createdCliente => {
        if (createdCliente) {
          this.clienteCreado = createdCliente;
          alert('cliente creado correctamente');
          this.formData.nitCliente = '';
          this.formData.nombre = '';
        } else {
          alert('error al crear al cliente');
        }
      }, error => {
        alert('error al crear al cliente');
      }
      );
  }

}
