import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AgregarPuntosComponent } from './agregar-puntos.component';

describe('AgregarPuntosComponent', () => {
  let component: AgregarPuntosComponent;
  let fixture: ComponentFixture<AgregarPuntosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AgregarPuntosComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AgregarPuntosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
