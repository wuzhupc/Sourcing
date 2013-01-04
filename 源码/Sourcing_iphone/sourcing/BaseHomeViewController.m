//
//  BaseHomeViewController.m
//  sourcing
//
//  Created by wuzhu on 13-1-4.
//  Copyright (c) 2013年 wuzhu. All rights reserved.
//

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Imports

#import "BaseHomeViewController.h"
#import "CustomNavigationBar.h"

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Types

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Defines & Constants

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Macros

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Private Interface

@interface BaseHomeViewController ()

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Private Properties

@end


////////////////////////////////////////////////////////////////////////////////
#pragma mark - Implementation

@implementation BaseHomeViewController

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Synthesize

/* Outlets ********************************************************************/

/* Public *********************************************************************/
@synthesize fatherchannel=_fatherchannel;

/* Private ********************************************************************/

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Setup & Teardown

- (void)commonInitBaseHomeViewController
{
}

- (id)initWithNibName:(NSString*)nibNameOrNil bundle:(NSBundle*)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self)
    {
        [self commonInitBaseHomeViewController];
    }
    return self;
}

- (id)initWithCoder:(NSCoder*)aDecoder
{
    self = [super initWithCoder:aDecoder];
    if (self)
    {
        [self commonInitBaseHomeViewController];
    }
    return self;
}

- (void)viewDidLoad {
    [super viewDidLoad];
    // your code here
    //自定义NavigationBar
    CustomNavigationBar* customNavigationBar2 = (CustomNavigationBar*)self.customNavigationBar;
    [customNavigationBar2 setBackgroundWith:[UIImage imageNamed:@"navigation_bg"]];
    //
    //if(self.titleNavigationItem!=nil&&self.fatherchannel!=nil)
    //    self.titleNavigationItem.title = self.fatherchannel.channelName;
}

- (void)viewDidUnload {
	// your code here
    [super viewDidUnload];
}

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Superclass Overrides

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Public methods
-(void)setNavTitle:(NSString *)ktitle
{
    self.tabBarItem.title = ktitle;
}

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Private methods

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Actions

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Delegate methods


@end
