import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProcesarPaqueteComponent } from './procesar-paquete.component';

describe('ProcesarPaqueteComponent', () => {
  let component: ProcesarPaqueteComponent;
  let fixture: ComponentFixture<ProcesarPaqueteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProcesarPaqueteComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ProcesarPaqueteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
