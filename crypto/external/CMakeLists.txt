add_subdirectory(argon2)
add_subdirectory(ed25519)

set_property(TARGET argon2 PROPERTY FOLDER "external")
set_property(TARGET ed25519 PROPERTY FOLDER "external")
