//
//  NewsViewController.m
//  sourcing
//
//  Created by wuzhu on 13-1-2.
//  Copyright (c) 2013年 wuzhu. All rights reserved.
//

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Imports

#import "NewsViewController.h"
#import "CustomNavigationBar.h"
#import "ChannelVO.h"
#import "FileUtil.h"
#import "JsonParser.h"
#import "MobileInfoService.h"
#import "NewsVO.h"
#import "ApplicationSet.h"

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Types

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Defines & Constants

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Macros

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Private Interface

@interface NewsViewController ()

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Private Properties

@end


////////////////////////////////////////////////////////////////////////////////
#pragma mark - Implementation

@implementation NewsViewController

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Synthesize

/* Outlets ********************************************************************/

/* Public *********************************************************************/

/* Private ********************************************************************/

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Setup & Teardown

- (void)commonInitNewsViewController
{
    [self.tabBarItem setFinishedSelectedImage:[UIImage imageNamed:@"icon_home_tb_news"] withFinishedUnselectedImage:[UIImage imageNamed:@"icon_home_tb_news"]];
    //self.tabBarController.tabBar.tintColor = [UIColor clearColor];
    //[self.tabBarController.tabBar setBackgroundImage:[UIImage imageNamed:@"tabbar_backgroundimage"]];
    //self.tabBarController.tabBar.selectionIndicatorImage = [UIImage imageNamed:@"tabbar_selectionindicatorimage"];
}

- (id)initWithNibName:(NSString*)nibNameOrNil bundle:(NSBundle*)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self)
    {
        [self commonInitNewsViewController];
    }
    return self;
}

- (id)initWithCoder:(NSCoder*)aDecoder
{
    self = [super initWithCoder:aDecoder];
    if (self)
    {
        [self commonInitNewsViewController];
    }
    return self;
}

- (void)viewDidLoad {
    [super viewDidLoad];
    [self initSubChannels];
}

- (void)viewDidUnload {
	// your code here
    subChannels = nil;
    subChannelBTV = nil;
    [self setCustomNavigationBar:nil];
    [self setTitleNavigationItem:nil];
    [self setSubChannelScrollView:nil];
    [super viewDidUnload];
}

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Superclass Overrides

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Public methods

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Private methods

-(void)initSubChannels
{
    if (self.fatherchannel!=nil) {
        subChannels = [ChannelVO getChannelsWithFatherID:[ApplicationSet shareData].channels father:self.fatherchannel.ChannelID];
    }
    if(subChannels==nil||[subChannels count]==0)
    {
        [self.subChannelScrollView setHidden:YES];
    }
    else
    {
        [self.subChannelScrollView setHidden:NO];
        NSMutableArray *channelnames = [[NSMutableArray alloc] initWithCapacity:[subChannels count]];
        for (ChannelVO *vo in subChannels) {
            [channelnames addObject:vo.channelName];
        }
        //TEST
        //[channelnames addObject:@"AAAA"];
        //[channelnames addObject:@"BBBB"];
        //
        subChannelBTV = [[BrowserTabView alloc] initWithTabTitles:channelnames andDelegate:self];
        
        [self.subChannelScrollView addSubview:subChannelBTV];
        //NSLog(@"%f %f",subChannelBTV.frame.size.height,subChannelBTV.frame.size.width);
        
        [self.subChannelScrollView setContentSize:subChannelBTV.frame.size];
    }
}

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Actions

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Delegate methods
-(void)BrowserTabView:(BrowserTabView *)browserTabView didSelecedAtIndex:(NSUInteger)index
{
    if (subChannels==0||index>[subChannels count]) {
        return;
    }
    ChannelVO *newselVO = [subChannels objectAtIndex:index];
    NSLog(@"%@",newselVO.channelName);
}

@end
