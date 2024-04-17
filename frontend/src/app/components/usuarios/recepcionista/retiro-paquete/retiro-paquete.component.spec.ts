import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RetiroPaqueteComponent } from './retiro-paquete.component';

describe('RetiroPaqueteComponent', () => {
  let component: RetiroPaqueteComponent;
  let fixture: ComponentFixture<RetiroPaqueteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RetiroPaqueteComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(RetiroPaqueteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
