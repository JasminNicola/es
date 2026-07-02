import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Bucket1Component } from './bucket1.component';

describe('Bucket1Component', () => {
  let component: Bucket1Component;
  let fixture: ComponentFixture<Bucket1Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Bucket1Component]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(Bucket1Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
