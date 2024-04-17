import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DesactivarUsuarioComponent } from './desactivar-usuario.component';

describe('DesactivarUsuarioComponent', () => {
  let component: DesactivarUsuarioComponent;
  let fixture: ComponentFixture<DesactivarUsuarioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DesactivarUsuarioComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DesactivarUsuarioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
