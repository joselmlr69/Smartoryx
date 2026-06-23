import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditarDireccion } from './editar-direccion';

describe('EditarDireccion', () => {
  let component: EditarDireccion;
  let fixture: ComponentFixture<EditarDireccion>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EditarDireccion]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditarDireccion);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
