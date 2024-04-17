import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DesactivarRutaComponent } from './desactivar-ruta.component';

describe('DesactivarRutaComponent', () => {
  let component: DesactivarRutaComponent;
  let fixture: ComponentFixture<DesactivarRutaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DesactivarRutaComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DesactivarRutaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
