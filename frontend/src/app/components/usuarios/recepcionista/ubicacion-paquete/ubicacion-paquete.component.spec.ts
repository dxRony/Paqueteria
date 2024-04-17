import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UbicacionPaqueteComponent } from './ubicacion-paquete.component';

describe('UbicacionPaqueteComponent', () => {
  let component: UbicacionPaqueteComponent;
  let fixture: ComponentFixture<UbicacionPaqueteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UbicacionPaqueteComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(UbicacionPaqueteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
