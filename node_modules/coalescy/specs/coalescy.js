describe( 'coalescy', function () {

  beforeEach( function () {
    this.coalesce = require( '../index' );
  } );

  it( 'should return the first non null value', function () {
    var result = this.coalesce( null, [] );
    expect( result ).to.deep.equal( [] );

    result = this.coalesce( null, {} );
    expect( result ).to.deep.equal( {} );

    result = this.coalesce( null, [], {} );
    expect( result ).to.deep.equal( [] );

    result = this.coalesce( null, undefined, 0, {} );
    expect( result ).to.equal( 0 );

    var a = null,
      b,
      c = 0,
      d = 1;

    result = this.coalesce( a, b, c, d );
    expect( result ).to.equal( 0 );
  } );

  it( 'should return null when no arguments are passed', function () {
    var result = this.coalesce();
    expect( result ).to.equal( null );
  } );
} );