import { ComponentFixture, TestBed } from '@angular/core/testing';

import { QuitarPuntosControlComponent } from './quitar-puntos-control.component';

describe('QuitarPuntosControlComponent', () => {
  let component: QuitarPuntosControlComponent;
  let fixture: ComponentFixture<QuitarPuntosControlComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [QuitarPuntosControlComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(QuitarPuntosControlComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
