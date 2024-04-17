import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IngresoPaqueteComponent } from './ingreso-paquete.component';

describe('IngresoPaqueteComponent', () => {
  let component: IngresoPaqueteComponent;
  let fixture: ComponentFixture<IngresoPaqueteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [IngresoPaqueteComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(IngresoPaqueteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
