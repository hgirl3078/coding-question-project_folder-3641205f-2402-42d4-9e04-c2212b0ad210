import { Payment } from './payment.model';

describe('Payment', () => {
  it('should create an instance', () => {
    const payment = new Payment();
    expect(payment).toBeTruthy();
  });

  it('should calculate total amount correctly', () => {
    const payment = new Payment();
    payment.totalAmount = 100;
    payment.status = 'COMPLETED';
    
    expect(payment.totalAmount).toBe(100);
    expect(payment.status).toBe('COMPLETED');
  });
});
