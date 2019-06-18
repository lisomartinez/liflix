package cloud.liso.liflix.showservice.service.authentication;

//@SpringBootTest
//@ActiveProfiles("dev")
class UserDetailsServiceImplTest {
//
//    @MockBean
//    private UserRepository userRepository;
//
//    @Autowired
//    @Qualifier("userDetailsServiceImpl")
//    private UserDetailsService userDetailsService;
//
//    @Test
//    void loadUserByUsername() {
//        User user = User.builder()
//                .withUserName("John")
//                .withPassword("123456")
//                .withEmail("johnDow@gmail.com")
//                .withRoles(new ArrayList<>(List.of(Role.USER)))
//                .build();
//
//        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));
//
//        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
//
//        assertThat(userDetails.getUsername()).isEqualTo(user.getUsername());
//        assertThat(userDetails.getPassword()).isEqualTo(user.getPassword());
//        assertThat(userDetails.getAuthorities()).isEqualTo(Set.of(new SimpleGrantedAuthority("Role_AUTHENTICATED"), new SimpleGrantedAuthority("Role_USER")));
//        assertThat(userDetails.isAccountNonExpired()).isTrue();
//        assertThat(userDetails.isAccountNonLocked()).isTrue();
//        assertThat(userDetails.isCredentialsNonExpired()).isTrue();
//        assertThat(userDetails.isEnabled()).isEqualTo(user.isActive());
//    }
}