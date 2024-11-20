import { Application } from './application.model';

describe('Application', () => {
  it('should create an instance', () => {
    const application = new Application();
    expect(application).toBeTruthy();
  });

  it('should validate required fields', () => {
    const application = new Application();
    application.applicationID = 1;
    application.applicationName = 'John Doe';
    application.status = 'PENDING';
    
    expect(application.applicationID).toBeDefined();
    expect(application.applicationName).toBeDefined();
    expect(application.status).toBeDefined();
  });
});
