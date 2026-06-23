import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistroDireccion } from './registro-direccion';

describe('RegistroDireccion', () => {
  let component: RegistroDireccion;
  let fixture: ComponentFixture<RegistroDireccion>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RegistroDireccion]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RegistroDireccion);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
