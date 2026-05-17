# GAC - Charity Actions Management Application

A comprehensive platform for managing charity actions and fundraising initiatives.

## Features

### 1. User Management
- User registration with email/password
- User login with JWT authentication
- User profile management (CRUD)
- Donation history tracking

### 2. Organization Management
- Organization registration with validation
- Super-admin approval system
- Organization profile management
- Logo and description uploads
- Link organizations to charity actions

### 3. Charity Actions
- Create, read, update, delete charity actions
- Categories: Education, Health, Environment, Poverty, Disaster Relief, Animal Welfare
- Track fundraising goals and progress
- Archive actions
- Image/video support

### 4. Exploration System
- Search and filter actions by category
- Popular actions listing
- Personalized recommendations

### 5. Donations & Participation
- Users can join charity actions
- WhatsApp/Cash donation support
- Track contributions and progress
- Donation confirmation emails

### 6. Multi-language Support
- English
- French (Français)
- Arabic (العربية)

## Tech Stack

- **Backend**: Spring Boot 3.x
- **Database**: H2 (In-memory)
- **Authentication**: JWT + Spring Security
- **Email**: Spring Mail
- **Frontend**: Thymeleaf + Tailwind CSS

## Installation & Setup

### Prerequisites
- Java 17+
- Maven 3.6+

### Build
```bash
mvn clean package
```

### Run
```bash
mvn spring-boot:run
```

The application will be available at `http://localhost:8080`

## API Endpoints

### Authentication
- `POST /api/auth/register` - Register new user
- `POST /api/auth/login` - Login user

### Users
- `GET /api/users/profile` - Get current user profile
- `GET /api/users/{id}` - Get user by ID
- `PUT /api/users/{id}` - Update user
- `DELETE /api/users/{id}` - Delete user

### Organizations
- `POST /api/organisations` - Create organization
- `GET /api/organisations/{id}` - Get organization
- `PUT /api/organisations/{id}` - Update organization
- `GET /api/organisations` - Get all organizations
- `GET /api/organisations/public/validated` - Get validated organizations
- `GET /api/organisations/admin/pending` - Get pending organizations (Admin)
- `POST /api/organisations/admin/{id}/validate` - Validate organization (Admin)
- `POST /api/organisations/admin/{id}/reject` - Reject organization (Admin)

### Charity Actions
- `POST /api/actions` - Create action
- `GET /api/actions/{id}` - Get action
- `PUT /api/actions/{id}` - Update action
- `POST /api/actions/{id}/archive` - Archive action
- `GET /api/actions` - Get all actions
- `GET /api/actions/public/active` - Get active actions
- `GET /api/actions/public/popular` - Get popular actions
- `GET /api/actions/organisation/{orgId}` - Get actions by organization
- `GET /api/actions/category/{category}` - Get actions by category

### Donations
- `POST /api/donations` - Create donation
- `GET /api/donations/{id}` - Get donation
- `POST /api/donations/{id}/confirm` - Confirm donation
- `GET /api/donations/user/{userId}` - Get user donations
- `GET /api/donations/action/{actionId}` - Get action donations
- `GET /api/donations/action/{actionId}/total` - Get total donations

### Participation
- `POST /api/participations` - Join action
- `GET /api/participations/{id}` - Get participation
- `DELETE /api/participations/{id}` - Leave action
- `GET /api/participations/user/{userId}` - Get user participations
- `GET /api/participations/action/{actionId}` - Get action participants
- `GET /api/participations/action/{actionId}/count` - Get participant count

## Database

The application uses H2 in-memory database. Access the H2 console at:
```
http://localhost:8080/h2-console
```

Connection settings:
- URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: (leave empty)

## Environment Variables

```bash
# JWT
JWT_SECRET=your-secret-key
JWT_EXPIRATION=86400000

# Mail (Optional)
MAIL_USERNAME=your-email@gmail.com
MAIL_PASSWORD=your-app-password

# OAuth2 (Optional)
GOOGLE_CLIENT_ID=your-client-id
GOOGLE_CLIENT_SECRET=your-client-secret
```

## Project Structure

```
src/main/java/com/test/gac_4/
├── config/              # Spring configuration
├── controller/          # REST controllers
├── dto/                 # Data Transfer Objects
├── entities/            # JPA entities
├── exception/           # Exception handling
├── repositories/        # Data access layer
├── security/            # Security configuration
└── services/            # Business logic

src/main/resources/
├── templates/           # Thymeleaf templates
├── messages*.properties # i18n messages
└── application.properties # Configuration
```

## Key Features Implementation

### 1. JWT Authentication
- Token generation on login/registration
- Token validation on each request
- Automatic role-based access control

### 2. Email Notifications
- Donation confirmations
- Organization validation emails
- Action update notifications

### 3. Multi-language
- Support for EN, FR, AR
- Configurable via Accept-Language header
- Messages stored in properties files

### 4. Data Validation
- JSR-303 Bean Validation
- Custom error responses
- Input sanitization

### 5. Exception Handling
- Global exception handler
- Custom error responses
- Proper HTTP status codes

## Future Enhancements

- [ ] Payment gateway integration (Stripe/PayPal)
- [ ] File upload service (AWS S3)
- [ ] Advanced search and filtering
- [ ] Rating and review system
- [ ] Social sharing features
- [ ] Push notifications
- [ ] Mobile app (Flutter/React Native)
- [ ] Analytics dashboard

## License

MIT License

## Support

For issues and questions, please contact: support@gac-app.com

---

**Version**: 1.0.0  
**Last Updated**: March 2026
